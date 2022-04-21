import {useEffect, useState} from "react";
import {Plant} from "../model";
import {useAuth} from "../Authentification/AuthProvider";
import {changePlants} from "../service/FrontendService";
import DeleteButton from "../CssComponents/DeleteButton";
import EditButton from "../CssComponents/EditButton";
import EditorInput from "../CssComponents/EditorInput";

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
        fetch(`${process.env.REACT_APP_BASE_URL}/api/plants/admin/${props.plantItem.id}`,{
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
                        <EditorInput value={scNameToEdit} onChange={setScNameToEdit} type={'text'} additionalCss="ml-2"/>
                        <EditorInput value={nonScNameToEdit} onChange={setNonScNameToEdit} type={'text'} additionalCss="ml-2"/>
                        <EditorInput value={locationToEdit} onChange={setLocationToEdit} type={'text'} additionalCss="ml-2"/>
                        <EditorInput value={pouringToEdit} onChange={setPouringToEdit} type={'text'} additionalCss={'ml-2'}/>
                        <EditorInput value={soilToEdit} onChange={setSoilToEdit} type={'text'} additionalCss={'ml-2'}/>
                        <EditorInput value={manureToEdit} onChange={setManureToEdit} type={'text'} additionalCss={'ml-2'}/>
                        <EditorInput value={repotToEdit} onChange={setRepotToEdit} type={'text'} additionalCss={'ml-2'}/>
                        <EditButton label="Änderungen speichern" onClick={editPlant}/>
                        <div>{error}</div>
                    </div>
                    :
                    <div className="border-solid bg-gray-200 my-2 ml-2 w-80" >
                        <p className="text-green-900">Wissenschaftl. Name: {scNameToEdit}</p>
                        <p className="text-green-900">Name: {nonScNameToEdit}</p>
                        <p className="text-green-900">Wo hinstellen: {locationToEdit}</p>
                        <p className="text-green-900">Wasserbedarf: {pouringToEdit}</p>
                        <p className="text-green-900">Bevorzugte Erde: {soilToEdit}</p>
                        <p className="text-green-900">Düngen: {manureToEdit}</p>
                        <p className="text-green-900">Umtopfen: {repotToEdit}</p>
                        <EditButton label="Editieren" onClick={()=>setEditMode(true)}/>
                        <DeleteButton label='Pflanze löschen' onClick={() => deletePlant}/>
                        <div>{error}</div>
                    </div>

            }

        </div>

    )
}