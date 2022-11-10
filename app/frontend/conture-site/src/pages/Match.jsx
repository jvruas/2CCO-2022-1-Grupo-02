import Header from "../components/Header"
import Perfil from "../components/PerfilSimples"
import MenuPerfil from "../components/MenuDisponivel"
import '../html-css-template/css/Match.css'
import Footer from "../components/Footer"
import HeaderMatch from "../components/headerMatch"
import Card from "../components/CardMatch"
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import apiProdutos from "../apiProduto.js"

function Match() {

   
    return (
        <>
            <Header></Header>
            <section className="section-match">
                <Perfil></Perfil>
                <MenuPerfil></MenuPerfil>
                <div className="header_match">
                
                <div className="cards">
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>

                </div>
                </div>

          
            </section>
            <Footer></Footer>
        </>
    )
}

export default Match;