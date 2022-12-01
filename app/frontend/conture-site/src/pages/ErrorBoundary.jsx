import React from 'react';
import CardComentarios from '../components/CardComentarios';

class ErrorBoundary extends React.Component {
    constructor(props) {
      super(props);
      this.state = { hasError: false };
    }
  
    componentDidCatch(error, info) {    
      // Mostra uma UI alternativa    
      this.setState({ hasError: true });    
      // Você também pode registrar o erro em um serviço de relatório de erros 
      // logErrorToMyService(error, info);  
    }
    render() {
      if (this.state.hasError) {      
        // Você pode renderizar qualquer alternativa de UI      
        return <CardComentarios
          
        >

        </CardComentarios>;    
      }    return this.props.children;
    }
  }

export default ErrorBoundary;