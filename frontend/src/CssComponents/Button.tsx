interface ButtonProps {
    label: string
    onClick: () => void
}

export default function Button(props: ButtonProps) {
    return (
        <button className="bg-slate-400 hover:bg-slate-700 px-5 py-2 leading-5 rounded-full font-semibold text-l text-indigo-100 buttonFont ml-2"
                onClick={props.onClick}>
            {props.label}
            </button>
    )
}