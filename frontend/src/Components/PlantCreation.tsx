import {FormEvent, useState} from "react";
import axios from "axios";
import {useAuth} from "../Authentification/AuthProvider";
import {Plant} from "../model";

interface PlantCreationProps {
    onPlantCreation: (plantItems : Array<Plant>) => void
}

export default function PlantCreation(props : PlantCreationProps) {

const [scName, setScName] = useState("")
const [nonScName, setNonScName] = useState("")
const [location, setLocation] = useState("")
const [pouring, setPouring] = useState("")
const [soil, setSoil]= useState("")
const [manure, setManure] = useState("")
const [repot, setRepot] = useState("")
const [error, setError] = useState("")
const auth = useAuth()

const createNewPlant = (event: FormEvent) => {
    event.preventDefault()
    setError('')
    axios.post(`${process.env.REACT_APP_BASE_URL}/api/plants/admin`,
        {
            'scientificName': scName, 'nonScName': nonScName, 'location': location,
            'pouring': pouring, 'soil': soil, 'manure': manure, 'repot': repot
        }, {headers: {'Authorization': `Bearer${auth.token}`}})
        .then(response => {
            if (response.status === 200){
                return response.data
            }
            throw new Error("Planting your seed went wrong")
        })
        .then((plantsFromBackend: Array<Plant>) => {
            setScName("")
            setNonScName("")
            setLocation("")
            setPouring("")
            setSoil("")
            setManure("")
            setRepot("")
            props.onPlantCreation(plantsFromBackend)
        })
        .catch()

}

return(
    <div>
        <form onSubmit={event => createNewPlant(event)}>
            <input type="text" placeholder={'Wissenschaftl. Name'} value={scName} onChange={event => setScName(event.target.value)}/>
            <input type="text" placeholder={'Name'} value={nonScName} onChange={event => setNonScName(event.target.value)}/>
            <input type="text" placeholder={'Wo hinstellen?'} value={location} onChange={event => setLocation(event.target.value)}/>
            <input type="text" placeholder={'Wasserbedarf'} value={pouring} onChange={event => setPouring(event.target.value)}/>
            <input type="text" placeholder={'Bevorzugter Boden'} value={soil} onChange={event => setSoil(event.target.value)}/>
            <input type="text" placeholder={'Düngen'} value={manure} onChange={event => setManure(event.target.value)}/>
            <input type="text" placeholder={'Umtopfen'} value={repot} onChange={event => setRepot(event.target.value)}/>
            <input type="submit" value={'Anlegen'}/>
        </form>
        {error && <h3>{error}</h3>}
    </div>
)
}