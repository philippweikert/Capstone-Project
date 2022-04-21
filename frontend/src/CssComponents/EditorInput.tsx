interface EditorInputProps {
    value: string
    onChange: (value: string) => void
    type?: string
    additionalCss?: string
}

export default function EditorInput(props: EditorInputProps){

    return (
        <input type={props.type ?? "text"}
               className={`border-b-2 my-2 ${props.additionalCss}`}
               value={props.value}
               onChange={ev => props.onChange(ev.target.value)}/>
    )
}