import axios from "axios";
import {Credentials, RegisterCredentials} from "../Interfaces";
import {Plant} from "../model";


export const loginUser = ({username, password} : Credentials) =>{
    return axios.post(`${process.env.REACT_APP_BASE_URL}/api/login`,
        {'username':username,'usedPassword': password})
        .then(response => response.data)
}

export const registerUser = ({username, password, repeatPassword} :RegisterCredentials) =>{
    return axios.post(`${process.env.REACT_APP_BASE_URL}/api/createuser`,
        {'username': username, 'password': password, 'repeatPassword':repeatPassword})
        .then(response => response.data)
}

export const searchPlant = (input: string, token: string) => {
    return axios.get(`${process.env.REACT_APP_BASE_URL}/api/plants/admin/search/${input}`,
        {headers: {'Authorization': `Bearer ${token}`}})
        .then(response => response.data)

}

export const getAllPlants = (token: string) => {
    return axios.get(`${process.env.REACT_APP_BASE_URL}/api/plants/admin`,
        {headers: {'Authorization':`Bearer ${token}`}})
        .then(response => response.data)
}

export const changePlants = (plant: Plant, token: string) => {
    return fetch(`${process.env.REACT_APP_BASE_URL}/api/plants/admin/${plant.id}`, {
        method: 'PUT',
    headers: {
        'Authorization' : `Bearer ${token}`,
    'Content-Type' : 'application/json'},
        body: JSON.stringify(plant)
    })
}