import {useAuth} from "../Authentification/AuthProvider";
import axios from "axios";
import {Plant} from "../model";
import {useState} from "react";

export default function UserForm() {
    const [searchPlant, setSearchPlant] = useState("")
    const [plants, setPlants] = useState([] as Array<Plant>)

    const auth = useAuth();

    const getSearchedPlant = (searching: string) => {
        axios.get(`${process.env.REACT_APP_BASE_URL}/api/plants/${searching}`,
            {headers: {'Authorization': `Bearer${auth.token}`}})
            .then((response => response.data))
            .then((responded: Array<Plant>) => {
                setPlants(responded)
            })
        setSearchPlant("")
    }


    const getAllMyPlants = () => {
        axios.get(`${process.env.REACT_APP_BASE_URL}/api/plants`,
            {headers: {'Authorization': `Bearer${auth.token}`}})
            .then(response => response.data)
            .then((plantsFromBackend: Array<Plant>) => setPlants(plantsFromBackend))

        return (
            <div>
                <div>
                    <input type={'text'} placeholder={'Gib hier die gesuchte Pflanze ein'} value={searchPlant}
                           onChange={searched => getSearchedPlant(searched.target.value)}/>
                    <input type={'submit'} value={'Suchen'}/>
                </div>
                <div>
                    <h3>Zeige mir alle meine Pflanzen an</h3>
                    <button onClick={getAllMyPlants}>Lass dir hier deine Pflanzen anzeigen</button>
                    <ul>
                        {plants.map(currentPlant => <li
                            key={currentPlant.id}>{currentPlant.scientificName}+{currentPlant.nonScName}+{currentPlant.location}
                            +{currentPlant.pouring}+{currentPlant.soil}+{currentPlant.manure}+{currentPlant.repot}</li>)}
                    </ul>
                </div>
            </div>
        )
    }
}