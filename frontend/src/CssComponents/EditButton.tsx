interface EditButtonProps {
    label: string
    onClick: () => void

}

export default function EditButton (props: EditButtonProps) {
    return (
        <button className="bg-slate-400 hover:bg-slate-700 px-5 py-2 my-2 rounded-md leading-5 font-semibold text-l text-white buttonFont "
                onClick={props.onClick}>
            {props.label}
        </button>
    )
}