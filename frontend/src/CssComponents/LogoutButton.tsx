interface LogoutButtonProps {
    label: string
    onClick: () => void
}

export default function LogoutButton (props: LogoutButtonProps) {
    return (
        <button className="hover:bg-slate-700 px-5 py-2 my-2 ml-10 leading-5 rounded-full font-semibold text-l text-white buttonFont "
                onClick={props.onClick}>
            {props.label}
        </button>
    )
}