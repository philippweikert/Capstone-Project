interface ButtonProps {
    label: string
    onClick: () => void
}

export default function Button(props: ButtonProps) {
    return (
        <button className="bg-green-600 hover:bg-green-800 px-5 py-2 leading-5 rounded-full font-semibold text-l text-white buttonFont ml-2"
                onClick={props.onClick}>
            {props.label}
            </button>
    )
}