interface PlantInputProps {
    value: string
    placeholder: string
    onChange: (value: string) => void
    type?: string
    additionalCss?: string
}

export default function PlantInput(props: PlantInputProps){

    return (
        <input type={props.type ?? "text"}
               className={`border-b-2 mt-5  ${props.additionalCss}`}
               placeholder={props.placeholder}
               value={props.value}
               onChange={ev => props.onChange(ev.target.value)}/>
    )
}