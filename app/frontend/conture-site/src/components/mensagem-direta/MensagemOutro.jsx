import '../../html-css-template/css/MensagemDireta.css';

function MensagemOutro(props) {

    return (
        <>
            <div className="md-msg-outro">
                <p>{props.mensagem}</p>
            </div>
        </>
    )
}

export default MensagemOutro;
