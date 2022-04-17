import {useEffect, useState} from "react";
import {Plant} from "../model";
import {getAllPlants} from "../service/FrontendService";
import {useAuth} from "../Authentification/AuthProvider";
import PlantCreation from "./PlantCreation";

export default function PlantList(){

    const [plants, setPlants] = useState([] as Array<Plant>)
    const [error, setErrorMessage] = useState('')

    const auth = useAuth()

    const fetchAll = () => {
        return getAllPlants(auth.token)
            .then(response => {
                if (response.status === 200) {
                    return response.data
                }
                throw new Error("Getting your List went wrong!")
            })
            .then((plantsFromBackend: Array<Plant>) => setPlants(plantsFromBackend))
            .catch((event : Error) => setErrorMessage(event.message))
    }

    useEffect(() => {
        fetchAll()
    }, [])

    useEffect(() => {
        setTimeout(() => {
            setErrorMessage('')
        }, 20000)
    })

    return (
        <div>
            <div>
                <PlantCreation onPlantCreation={setPlants}/>
            </div>
            <div>
                <ul>
                <h4>
                    {plants.map(plants =>
                        <div>
                            <p>Wissenschaftl. Name: {plants.scientificName}</p>
                            <p>Name: {plants.nonScName}</p>
                            <p>Wohin stellen?: {plants.location}</p>
                            <p>Befeuchtung: {plants.pouring}</p>
                            <p>Boden: {plants.soil}</p>
                            <p>Düngen: {plants.manure}</p>
                            <p>Umtopfen: {plants.repot}</p>
                        </div>)}
                </h4>
                </ul>
            </div>
        </div>
    )
}

