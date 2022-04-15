import {Plant} from "../model";
import {useState} from "react";


interface DeleteAndEditProps {
    plantItem : Plant
    onPlantDeletion: () => void
    onPlantChange: (plant: Array<Plant>) => void
}
export default function DeleteAndEdit (props: DeleteAndEditProps) {

    const [scNameToEdit, setScNameToEdit] = useState(props.plantItem.scientificName)
    const [nonScToEdit, setNonScToEdit] = useState(props.plantItem.nonScName)
    const [locationToEdit, setLocationToEdit] = useState(props.plantItem.location)
    const [pouringToEdit, setPouringToEdit] = useState(props.plantItem.pouring)
    const [soilToEdit, setSoilToEdit] = useState(props.plantItem.soil)
    const [manureToEdit, setManureToEdit] = useState(props.plantItem.manure)
    const [repotToEdit, setRepotToEdit] = useState(props.plantItem.repot)
}