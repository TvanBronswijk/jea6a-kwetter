//src: https://hptechblogs.com/using-json-web-token-react/
import decode from 'jwt-decode';
import JSOG from 'jsog';

export default class AuthTokenService {

    static TOKEN_ID = "id_token";

    constructor() {
        AuthTokenService.fetch = AuthTokenService.fetch.bind(this);
    }

    static login(username, password) {
        let body = JSON.stringify({username, password});
        return AuthTokenService.fetch(`/api/auth/token/request`, {
            method: 'POST',
            body: body,
        }).then(result => {
            AuthTokenService.setToken(result.token);
            return Promise.resolve(result);
        });
    }

    static loggedIn() {
        const token = AuthTokenService.getToken();
        return !!token && !AuthTokenService.isTokenExpired(token);
    }

    static isTokenExpired(token) {
        try {
            const profile = decode(token);
            return profile.exp < Date.now() / 1000;
        }
        catch (err) {
            return false;
        }
    }

    static setToken(token) {
        localStorage.setItem(this.TOKEN_ID, token);
    }

    static getToken() {
        return localStorage.getItem(this.TOKEN_ID);
    }

    static logout() {
        localStorage.removeItem(this.TOKEN_ID);
    }

    static getProfile() {
        const token = AuthTokenService.getToken();
        if (!token) {
            throw new Error("Could not load profile. No token found.");
        }
        return token ? decode(token) : null;
    }

    static fetch(url, options) {
        // performs api calls sending the required authentication headers
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };

        if (AuthTokenService.loggedIn()) {
            headers['Authorization'] = 'Bearer ' + AuthTokenService.getToken();
        }

        return fetch(url, {headers, ...options})
            .then(AuthTokenService._checkStatus)
            .then(response => response.json())
            .then(json => JSOG.decode(json));
    }

    static _checkStatus(response) {
        if (response.status >= 200 && response.status < 300) {
            return response;
        } else {
            let error = new Error(response.statusText);
            error.response = response;
            throw error;
        }
    }
}