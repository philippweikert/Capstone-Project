import axios from "axios";
import {Plant} from "./model";


export const createNewPlant = (plant : Plant) => {
      return axios.post(`${process.env.REACT_APP_BASE_URL}/api/plants`,
            plant)
            .then(response => response.data)

    }




