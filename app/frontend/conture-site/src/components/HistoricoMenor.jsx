import '../html-css-template/css/HistoricoMenor.css'

function HistoricoMenor(props){

    let dataCad = new Date(props.dataCon);
   return (
    
        <>
        <div class="historico">
        <div class="historico_sup">
            <div>Tipo</div>
            <div>Tipo equipamento</div>
            <div>Data</div>
        </div>
        <div class="historico_inf">
            <div>{props.tipo}</div>
            <div> {props.tipoEquipamento}</div>
            <div>{dataCad.getDate()}/{dataCad.getMonth()}/{dataCad.getFullYear()}</div>
        </div>
    </div>
    </>
    );
}
export default HistoricoMenor;