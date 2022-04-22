import {FormEvent, useState} from "react";
import axios from "axios";
import {useAuth} from "../Authentification/AuthProvider";
import {Plant} from "../model";
import PlantInput from "../CssComponents/PlantInput";
import Button from "../CssComponents/Button";


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


}

return(
    <div>
        <form onSubmit={event => createNewPlant(event)}>
            <PlantInput placeholder={'wissenschaftl. Name'} value={scName} onChange={setScName} type='text' additionalCss="mr-2 ml-3"/>
            <PlantInput placeholder={'Name'} value={nonScName} onChange={setNonScName} type='text' additionalCss="mr-2"/>
            <PlantInput placeholder={'Wo hinstellen?'} value={location} onChange={setLocation} type='text' additionalCss="mr-2"/>
            <PlantInput placeholder={'Wasserbedarf'} value={pouring} onChange={setPouring} type='text' additionalCss="mr-2"/>
            <PlantInput placeholder={'Bevorzugter Boden'} value={soil} onChange={setSoil} type='text' additionalCss="mr-2"/>
            <PlantInput placeholder={'Düngen'} value={manure} onChange={setManure} type='text' additionalCss="mr-2"/>
            <PlantInput placeholder={'Umtopfen'} value={repot} onChange={setRepot} type='text' additionalCss="mr-2"/>
            <Button label='Anlegen' onClick={() => createNewPlant}/>
        </form>
        {error && <h3>{error}</h3>}
    </div>
)
}