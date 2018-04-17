//Source: https://hptechblogs.com/using-json-web-token-react/
import decode from 'jwt-decode';

export default class AuthTokenService {

    constructor() {
        AuthTokenService.fetch = AuthTokenService.fetch.bind(this); // React binding stuff
    }

    static login(username, password) {
        // Get a token from api server using the fetch api
        let body = JSON.stringify({username, password});
        return AuthTokenService.fetch(`/api/tokens/request`, {
            method: 'POST',
            body: body,
        }).then(result => {
            AuthTokenService.setToken(result.token); // Setting the token in localStorage
            return Promise.resolve(result);
        });
    }

    static loggedIn() {
        // Checks if there is a saved token and it's still valid
        const token = AuthTokenService.getToken(); // Getting token from localstorage
        return !!token && !AuthTokenService.isTokenExpired(token); // handwaiving here
    }

    static isTokenExpired(token) {
        try {
            const decoded = decode(token);
            return decoded.exp < Date.now() / 1000;
        }
        catch (err) {
            return false;
        }
    }

    static setToken(idToken) {
        // Saves user token to localStorage
        localStorage.setItem('id_token', idToken);
    }

    static getToken() {
        // Retrieves the user token from localStorage
        return localStorage.getItem('id_token');
    }

    static logout() {
        // Clear user token and profile data from localStorage
        localStorage.removeItem('id_token');
    }

    static getProfile() {
        // Using jwt-decode npm package to decode the token
        return decode(AuthTokenService.getToken());
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
            .then(response => response.json());
    }

    static _checkStatus(response) {
        // raises an error in case response status is not a success
        if (response.status >= 200 && response.status < 300) { // Success status lies between 200 to 300
            return response;
        } else {
            let error = new Error(response.statusText);
            error.response = response;
            throw error;
        }
    }
}