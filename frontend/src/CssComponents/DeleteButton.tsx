interface DeleteButtonProps {
    label: string
    onClick: () => void
}

export default function DeleteButton (props: DeleteButtonProps) {
    return (
        <button className="bg-slate-400 hover:bg-red-700 px-5 py-2 my-2 leading-5 rounded-md font-semibold text-l text-white buttonFont "
                onClick={props.onClick}>
            {props.label}
        </button>
    )
}