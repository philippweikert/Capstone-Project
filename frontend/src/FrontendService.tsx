import axios from "axios";
import {Plant} from "./model";
import {Credentials, RegisterCredentials} from "./Interfaces";


export const createNewPlant = (plant : Plant) => {
      return axios.post(`${process.env.REACT_APP_BASE_URL}/api/plants/admin`,
            plant)
            .then(response => response.data)

}

export const loginUser = ({username, password} : Credentials) =>{
    return axios.post(`${process.env.REACT_APP_BASE_URL}/api/login`,
        {'username':username,'password': password})
        .then(response => response.data)
}

export const registerUser = ({username, password, repeatPassword} :RegisterCredentials) =>{
    return axios.post(`${process.env.REACT_APP_BASE_URL}/api/createuser`,
        {'username': username, 'password': password, 'repeatPassword':repeatPassword})
        .then(response => response.data)
}



