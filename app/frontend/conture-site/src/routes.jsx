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
import Avaliacao from "./pages/Avaliacao";
import CadastroProduto from "./pages/CadastroProduto";

function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<Login/>} />
                <Route path="/email-esqueci-senha" element={<PopUp/>} />
                <Route path="/esqueci-senha" element={<EsqueciSenha/>} />
                <Route path="/cadastro" element={<Cadastro/>} />
                <Route path="*" element={<Feed/>} />
                <Route path="/mensagem-direta" element={<MensagemDireta/>} />
                <Route path="/validacao-usuario" element={<ValidacaoUsuario/>} />
                <Route path="/validacao-usuario-confirmada" element={<ValidacaoUsuarioConfirmada/>} />
                <Route path="/alterar-senha" element={<AlterarSenha/>} />
                <Route path="/editar-perfil" element={<EditarPerfil/>} />
                <Route path="/desabilitar-perfil" element={<DesabilitarPerfil/>} />
                <Route path="/historico-pessoal" element={<HistoricoPessoal/>} />
                <Route path="/historico" element={<Historico/>} />
                <Route path="/disponivel" element={<Disponivel/>} />
                <Route path="/avaliacao" element={<Avaliacao/>} />
                <Route path="/cadastro-produto" element={<CadastroProduto/>} />
            </Routes>
        </BrowserRouter>
    );
}

export default Rotas;