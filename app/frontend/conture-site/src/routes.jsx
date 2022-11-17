import { BrowserRouter, Route, Routes } from "react-router-dom";
import EsqueciSenha from "./pages/EsqueciSenha";
import Login from "./pages/Login";
import Cadastro from "./pages/Cadastro";
import PopUp from "./pages/PopUp";
import Feed from "./pages/Feed";
import MensagemDireta from "./pages/MensagemDireta";
import ValidacaoUsuario from "./pages/ValidacaoUsuario";
import ValidacaoUsuarioConfirmada from "./pages/ValidacaoUsuarioConfirmada";
import AlterarSenha from "./pages/AlterarSenha";
import EditarPerfil from "./pages/EditarPerfil";
import DesabilitarPerfil from "./pages/DesabilitarPerfil";
import HistoricoPessoal from "./pages/HistoricoPessoal";
import Historico from "./pages/Historico";
import Disponivel from "./pages/Disponivel";
import DisponivelPessoal from "./pages/DisponivelPessoal";
import Avaliacao from "./pages/Avaliacao";
import AvaliacaoPessoal from "./pages/AvaliacaoPessoal";
import CadastroProduto from "./pages/CadastroProduto";
import PopUpFiltro from "./pages/pop-up/PopUpFiltro";
import PopUpReporte from "./pages/pop-up/PopUpReporte";
import PopUpAvaliacao from "./pages/pop-up/PopUpAvaliacao";
import PopUpDeletar from "./pages/pop-up/PopUpDeletar";
import DescricaoProduto from "./pages/DescricaoProduto";
import Dashboard from "./pages/Dashboard";
import Pesquisa from "./pages/Pesquisa";
import Match from "./pages/Match";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login/>} />
                <Route path="/email-esqueci-senha" element={<PopUp/>} />
                <Route path="/esqueci-senha" element={<EsqueciSenha/>} />
                <Route path="/cadastro" element={<Cadastro/>} />
                <Route path="*" element={<Feed/>} />
                <Route path="/feed" element={<Feed/>} />
                <Route path="/mensagem-direta" element={<MensagemDireta/>} />
                <Route path="/validacao-usuario" element={<ValidacaoUsuario/>} />
                <Route path="/validacao-usuario-confirmada" element={<ValidacaoUsuarioConfirmada/>} />
                <Route path="/alterar-senha" element={<AlterarSenha/>} />
                <Route path="/editar-perfil" element={<EditarPerfil/>} />
                <Route path="/desabilitar-perfil" element={<DesabilitarPerfil/>} />
                <Route path="/historico-pessoal" element={<HistoricoPessoal/>} />
                <Route path="/historico" element={<Historico/>} />
                <Route path="/disponivel" element={<Disponivel/>} />
                <Route path="/disponivel-pessoal/:id" element={<DisponivelPessoal/>} />
                <Route path="/avaliacao" element={<Avaliacao/>} />
                <Route path="/avaliacao-pessoal" element={<AvaliacaoPessoal/>} />
                <Route path="/cadastro-produto" element={<CadastroProduto/>} />
                <Route path="/popup" element={<PopUp/>} />
                <Route path="/popup-reporte" element={<PopUpReporte/>} />
                <Route path="/descricao-produto" element={<DescricaoProduto/>} />
                <Route path="/dashboard-admin-bi" element={<Dashboard/>}></Route>
                <Route path="/popup-avaliacao" element={<PopUpAvaliacao/>} />
                <Route path="/popup-filtro" element={<PopUpFiltro/>} />
                <Route path="/popup-deletar" element={<PopUpDeletar/>} />
                <Route path="/pesquisa" element={<Pesquisa/>} />
                <Route path="/match" element={<Match/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;