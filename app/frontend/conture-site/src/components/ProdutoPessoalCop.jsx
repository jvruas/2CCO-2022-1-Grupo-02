import "../html-css-template/css/produto.css"
import trash from "../html-css-template/imagens/trash 2.svg"
import vector from "../html-css-template/imagens/Vector.svg"
import foto from "../html-css-template/imagens/Rectangle 44.svg"

function Produto(props) {

    // const [produto, setProduto] = useState([]);
    // // const [usuarioImg, setUsuarioImg] = useState([]);

    // useEffect(() => {
    //     let idUsuario = sessionStorage.getItem('idUsuarioLogado');
    //     apiProduto.get(`/disponiveis?idDoador=${idUsuario}`).then((resposta) => {
    //         try {
    //             console.log(resposta.data)
    //             setProduto(resposta.data)
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     })
    // }, [])


    return (

        <div className="container-produto">
            <div className="informacoes-produto">
                <div className="superior"><img src={trash} alt="" /> <div class="visualizacao"><img src={vector} alt="" /><p>{props.visualizacao}</p></div></div>
                <div className="meio"><img src={foto} alt="" /></div>
                <div className="inferior"><p>{props.nome}</p></div>
            </div>
        </div>
    );
}

export default Produto
