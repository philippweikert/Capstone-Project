import {FormEvent, useCallback, useEffect, useState} from "react";
import axios from "axios";
import {useAuth} from "../Authentification/AuthProvider";
import {Plant} from "../model";

export default function AdminForm(){

    const [scientificName, setScientificName] = useState("");
    const [nonScName, setNonScName] = useState("");
    const [location, setLocation] = useState("");
    const [pouring, setPouring] = useState("");
    const [soil, setSoil] = useState("");
    const [manure, setManure] = useState("");
    const [repot, setRepot] = useState("");
    const [error, setError] = useState("")
    const [plants, setPlants] = useState([] as Array<Plant>)

    const auth = useAuth();

    const createNewPlant = (event: FormEvent) => {
        event.preventDefault()
        setError('')
        axios.post(`${process.env.REACT_APP_BASE_URL}/api/plants/admin`,
            {
                'scientificName': scientificName, 'nonScName': nonScName, 'location': location,
                'pouring': pouring, 'soil': soil, 'manure': manure, 'repot': repot
            }, {headers: {'Authorization': `Bearer${auth.token}`}})
            .then(response => response.data)
            .catch(e => {
                if (e.response.status === 400) {
                    setError("Pflanze schon vorhanden")
                } else {
                    setError(e.message)
                }
            })
            .then(getAllPlants)

        setScientificName("")
        setNonScName("")
        setLocation("")
        setPouring("")
        setSoil("")
        setManure("")
        setRepot("")

    }
        const getAllPlants = useCallback( () => {
           axios.get(`${process.env.REACT_APP_BASE_URL}/api/plants/admin`,
               {headers: {'Authorization':`Bearer${auth.token}`}})
                .then(response => response.data)
                .then((plantsFromBackend: Array<Plant>) => setPlants(plantsFromBackend))
        },[auth.token])

        useEffect(() => {
            getAllPlants()
        },[getAllPlants])


return(
    <div>
        <form onSubmit={event => createNewPlant(event)}>
            <input type="text" placeholder={'Wissenschaftl. Name'} value={scientificName} onChange={event => setScientificName(event.target.value)}/>
            <input type="text" placeholder={'Name'} value={nonScName} onChange={event => setNonScName(event.target.value)}/>
            <input type="text" placeholder={'Wo hinstellen?'} value={location} onChange={event => setLocation(event.target.value)}/>
            <input type="text" placeholder={'Wasserbedarf'} value={pouring} onChange={event => setPouring(event.target.value)}/>
            <input type="text" placeholder={'Bevorzugter Boden'} value={soil} onChange={event => setSoil(event.target.value)}/>
            <input type="text" placeholder={'Düngen'} value={manure} onChange={event => setManure(event.target.value)}/>
            <input type="text" placeholder={'Umtopfen'} value={repot} onChange={event => setRepot(event.target.value)}/>
            <input type="submit" value={'Anlegen'}/>
        </form>
        {error && <h3>{error}</h3>}
        <div>
            <h3>Alle Pflanzen in der Datenbank</h3>
            <ul>
            {plants.map(currentPlant => <li key={currentPlant.id}>{currentPlant.scientificName} | {currentPlant.nonScName} | {currentPlant.location} | {currentPlant.pouring} | {currentPlant.soil} | {currentPlant.manure} | {currentPlant.repot}</li>)}
            </ul>
        </div>
    </div>

)
}