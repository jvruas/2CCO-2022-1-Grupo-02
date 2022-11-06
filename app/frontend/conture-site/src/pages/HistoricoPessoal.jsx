
import Header from "../components/Header"
import Perfil from "../components/Perfil"
import MenuPerfil from "../components/MenuHistoricoPessoal"
import '../html-css-template/css/Style.css'
import '../html-css-template/css/HistoricoPessoalPage.css'
import Footer from "../components/Footer"
import Historico from "../components/HistoricoMaior"
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto"
import apiUsuario from "../apiUsuario"

function HistoricoDonatario() {

    const [historico, setHistorico] = useState([]);
    const [usuario, setUsuario] = useState([]);
    let idUsuario;
    
    useEffect(() => {
        idUsuario = sessionStorage.getItem('idUsuarioLogado');
        apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
            try {
                console.log("uijhhjh", resposta.data)
                setHistorico(resposta.data)
            } catch (error) {
                console.log(error)
            }
        })
        let dataCad = new Date(historico.dataConclusao);




        // if(select_opcoes.value == 0){
        //     apiProdutos.get(`/status?idDoador=${idUsuario}?todos`).then((resposta) => {
        //         try {
        //             console.log(resposta.data)
        //             setHistorico(resposta.data)
        //         } catch (error) {
        //             console.log(error)
        //         }
        //     })
        // }

        apiUsuario.get(`/${idUsuario}`).then((usuarioResposta) => {
            try {
                console.log("ofdoidsfodsfoifdsj", usuarioResposta.data)
                setUsuario(usuarioResposta.data)
            } catch (error) {
                console.log(error)
            }
        })

    }, [])

    const teste = (event) => {

        if (event.target.value == 0) {
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    setHistorico(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })
        }

        if (event.target.value == 1) {
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    setHistorico(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })
        }
        if (event.target.value == 2) {
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    setHistorico(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })
        }
        if (event.target.value == 3) {
            apiProdutos.get(`/status?idDoador=${idUsuario}&status=todos`).then((resposta) => {
                try {
                    console.log(resposta.data)
                    setHistorico(resposta.data)
                } catch (error) {
                    console.log(error)
                }
            })
        }

    }


    return (
        <>
            <Header></Header>
            <section id="hp-section">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div id="conteiner">
                    <div id="div_sup">
                        <b><p>Histórico</p></b>

                        <div id="div_filtro"><p>Filtrar por</p>
                            <select onChange={((event) => teste(event))} name="" id="select_opcoes">
                                <option value="0">Todos</option>
                                <option value="1">Processo</option>
                                <option value="2">Doados</option>
                                <option value="3">Recebidos</option>
                            </select>
                        </div>

                    </div>
                    <div className="div_historicos">
                        {historico != undefined && historico.length > 0 ? historico.map((historico) => (
                            <Historico
                                tipo={historico.tipo}
                                tipoEquipamento={historico.categoriaProduto}
                                equipamento={historico.nome}
                                negociante={usuario.nome}
                                dataCon={historico.dataConclusao}
                            />
                        )) : ""}


                    </div>
                </div>
            </section>
            <Footer></Footer>
        </>
    )
}

export default HistoricoDonatario;