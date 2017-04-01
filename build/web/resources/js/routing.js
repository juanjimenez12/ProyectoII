/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function routing(vistaMostrar)
{ 
    var vistas = ['GU-Ver','GU-Modificar'];
    for (i=0;i<vistas.length ; i++)
    {
        document.getElementById(vistas[i]).style.display = 'none';
    }
    mostrarVista(vistaMostrar); 
}

function mostrarVista(vistaMostrar)
{
    
     document.getElementById(vistaMostrar).style.display = 'block';
}



