import '../html-css-template/css/HistoricoMaior.css'

function HistoricoMaior(props){
    return (
        <>
        <div class="historico">
        <div class="historico_sup">
            <div>Tipo</div>
            <div>Tipo equipamento</div>
            <div>Equipamento</div>
            <div>Negociante</div>
            <div>Data</div>
        </div>

        <div class="historico_inf">
            <div>{props.tipo}</div>
            <div>{props.tipoEquipamento}</div>
            <div>{props.equipamento}</div>
            <div>{props.negociante}</div>
            <div>{props.data}</div>
        </div>
    </div>
    </>
    );
}
export default HistoricoMaior;