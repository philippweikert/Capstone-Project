import {FormEvent, useState} from "react";
import {createNewPlant} from "./FrontendService"

export default function PlantForm(){

    const [scientificName, setScientificName] = useState("");
    const [nonScName, setNonScName] = useState("");
    const [location, setLocation] = useState("");
    const [pouring, setPouring] = useState("");
    const [soil, setSoil] = useState("");
    const [manure, setManure] = useState("");
    const [repot, setRepot] = useState("");
    //const [errorMessage, setErrorMessage] = useState("");

    const createPlant = (event: FormEvent) => {
        event.preventDefault()
        createNewPlant({
            scientificName, nonScName, location, pouring, manure, soil, repot
        })
            .then(() => {
                setScientificName("")
                setNonScName("")
                setLocation("")
                setPouring("")
                setSoil("")
                setManure("")
                setRepot("")
            })
    }

return(
    <div>
        <form onSubmit={event => createPlant(event)}>
            <input type="text" placeholder={'Wissenschaftl. Name'} value={scientificName} onChange={event => setScientificName(event.target.value)}/>
            <input type="text" placeholder={'Name'} value={nonScName} onChange={event => setNonScName(event.target.value)}/>
            <input type="text" placeholder={'Wo hinstellen?'} value={location} onChange={event => setLocation(event.target.value)}/>
            <input type="text" placeholder={'Gießrhythmus'} value={pouring} onChange={event => setPouring(event.target.value)}/>
            <input type="text" placeholder={'Bevorzugter Boden'} value={soil} onChange={event => setSoil(event.target.value)}/>
            <input type="text" placeholder={'Düngen'} value={manure} onChange={event => setManure(event.target.value)}/>
            <input type="text" placeholder={'Umtopfen'} value={repot} onChange={event => setRepot(event.target.value)}/>
            <input type="submit" value={'Anlegen'}/>
        </form>
    </div>
)
    }