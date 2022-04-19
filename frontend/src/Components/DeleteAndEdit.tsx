import {useEffect, useState} from "react";
import {Plant} from "../model";
import {useAuth} from "../Authentification/AuthProvider";
import {changePlants} from "../service/FrontendService";

interface DeleteAndEditProps {

    plantItem: Plant
    onPlantChange: () => void
}

export default function DeleteAndEdit(props: DeleteAndEditProps) {

    const [scNameToEdit, setScNameToEdit] = useState(props.plantItem.scientificName);
    const [nonScNameToEdit, setNonScNameToEdit] = useState(props.plantItem.nonScName);
    const [locationToEdit, setLocationToEdit] = useState(props.plantItem.location);
    const [pouringToEdit, setPouringToEdit] = useState(props.plantItem.pouring);
    const [soilToEdit, setSoilToEdit] = useState(props.plantItem.soil);
    const [manureToEdit, setManureToEdit] = useState(props.plantItem.manure);
    const [repotToEdit, setRepotToEdit] = useState(props.plantItem.repot);
    const [error, setErrorMessage] = useState("")
    const [editMode, setEditMode] = useState(false)

    const auth = useAuth();

    const editPlant = () => {
        changePlants({
            id: props.plantItem.id,
            scientificName: scNameToEdit,
            nonScName: nonScNameToEdit,
            location: locationToEdit,
            pouring: pouringToEdit,
            soil: soilToEdit,
            manure: manureToEdit,
            repot: repotToEdit
        }, auth.token)
            .then(response => {
                if(response.status === 200) {
                    return response.json()
                }
                throw new Error("Edit went wrong!")
            })
            .then(() => {
            props.onPlantChange()
            setEditMode(false)
        })
        .catch((event: Error) => setErrorMessage(event.message))
    }

    const deletePlant = () => {
        fetch(`${process.env.REACT_APP_BASE}/api/plants/admin/${props.plantItem.id}`,{
            method: 'Delete',
            headers: {
                'Authorization' : `Bearer ${auth.token}`,
                'Content-Type' : 'application/json'
            }
        })
            .then(response => {
                if (response.status === 201) {
                    throw new Error("Delete did not work!")
                }
            })
             .then(() => props.onPlantChange())
            .catch((event : Error) => setErrorMessage(event.message))

    }

    useEffect(() => {
        setTimeout(() => {setErrorMessage('')}, 5000)
    })

    return(
        <div>
            {
                editMode
                ?
                    <div>
                        <input type={'text'} value={scNameToEdit} onChange={event => setScNameToEdit(event.target.value)}/>
                        <input type={'text'} value={nonScNameToEdit} onChange={event => setNonScNameToEdit(event.target.value)}/>
                        <input type={'text'} value={locationToEdit} onChange={event =>setLocationToEdit(event.target.value)}/>
                        <input type={'text'} value={pouringToEdit} onChange={event => setPouringToEdit(event.target.value)}/>
                        <input type={'text'} value={soilToEdit} onChange={event => setSoilToEdit(event.target.value)}/>
                        <input type={'text'} value={manureToEdit} onChange={event => setManureToEdit(event.target.value)}/>
                        <input type={'text'} value={repotToEdit} onChange={event => setRepotToEdit(event.target.value)}/>
                        <button onClick={editPlant}>Änderungen speichern</button>
                        <div>{error}</div>
                    </div>
                    :
                    <div>
                        <p>{scNameToEdit}</p> {/*auf <p> ändern*/}
                        <p>{nonScNameToEdit}</p>
                        <p>{locationToEdit}</p>
                        <p>{pouringToEdit}</p>
                        <p>{soilToEdit}</p>
                        <p>{manureToEdit}</p>
                        <p>{repotToEdit}</p>
                        <div>{error}</div>
                    </div>
            }
            <div>
                <button onClick={() => setEditMode(true)}>Editieren</button>
                <button onClick={deletePlant}>Pflanze löschen</button>
            </div>
        </div>

    )
}