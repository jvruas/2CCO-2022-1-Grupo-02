import '../../html-css-template/css/MensagemDireta.css';

function MensagemUsuario(props) {

    return (
        <>
            <div className="md-msg-eu">
                <p>{props.mensagem}</p>
            </div>
        </>
    )
}

export default MensagemUsuario;
