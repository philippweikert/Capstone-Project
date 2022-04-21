import {useCallback, useEffect, useState} from "react";
import {Plant} from "../model";
import {getAllPlants} from "../service/FrontendService";
import {useAuth} from "../Authentification/AuthProvider";
import PlantCreation from "./PlantCreation";
import DeleteAndEdit from "./DeleteAndEdit";

export default function PlantList(){

    const [plants, setPlants] = useState([] as Array<Plant>)
    const [error, setErrorMessage] = useState('')

    const auth = useAuth()


    const getPlantsToList = useCallback(() => {
        return getAllPlants(auth.token)
                .then((plantsFromBackend: Array<Plant>) => setPlants(plantsFromBackend)
                )},[auth.token])

    useEffect(() => {
        getPlantsToList()
            .catch((event: Error) => setErrorMessage(event.message))
    },[getPlantsToList])


    useEffect(() => {
        setTimeout(() => {
            setErrorMessage('')
        }, 20000)
    })

    return (
        <div>
            <div>
                <PlantCreation onPlantCreation={getPlantsToList}/>
            </div>
            <div>
                <ul>
                <div>
                    {plants &&
                        plants.map(plant => <DeleteAndEdit plantItem={plant} onPlantChange={getPlantsToList}/> )
                        }
                </div>
                    {error && <h3>{error}</h3>}
                </ul>
            </div>
        </div>
    )
}

