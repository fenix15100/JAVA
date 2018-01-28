<?php

$archivo=$_REQUEST['id'];
$body=file_get_contents( 'php://input');
$log="log.txt";




if ($_SERVER['REQUEST_METHOD'] == 'GET' ){
    if(file_exists("DATA/".$archivo)){
        $contenido=file_get_contents("DATA/".$archivo);
        echo $contenido;
        file_put_contents($log,' METHOD: GET FILE: '.$archivo." OK\n",FILE_APPEND);

    }else{
        echo 'Fichero no encontrado';
        file_put_contents($log,' METHOD: GET FILE: '.$archivo." ERROR\n",FILE_APPEND);
        
    }







} else if ($_SERVER['REQUEST_METHOD'] == 'POST' ){
    if(file_exists("DATA/".$archivo)){

        file_put_contents("DATA/".$archivo,$body);
        echo 'OK fichero actualidado';
        file_put_contents($log,' METHOD: POST FILE: '.$archivo." OK\n",FILE_APPEND);
        

    }else{
        echo 'El fichero no existe, crealo antes';
        file_put_contents($log,' METHOD: POST FILE: '.$archivo." ERROR\n",FILE_APPEND);
        

    }






} else if ($_SERVER['REQUEST_METHOD'] == 'PUT' ){

    if(file_exists("DATA/".$archivo)){
        echo 'El fichero ya existe';
        file_put_contents($log,' METHOD: PUT FILE: '.$archivo." ERROR\n",FILE_APPEND);
       
    }
    else{
        file_put_contents("DATA/".$archivo,$body);
        echo 'OK fichero creado';
        file_put_contents($log,' METHOD: PUT FILE: '.$archivo." OK\n",FILE_APPEND);
        

    }




} else if ($_SERVER['REQUEST_METHOD'] == 'DELETE' ){
    if(file_exists("DATA/".$archivo)){
        unlink("DATA/".$archivo);
        echo 'Archivo Borrado corectamente';
        file_put_contents($log,' METHOD: DELETE FILE: '.$archivo." OK\n",FILE_APPEND);

    

    }else{
        
        echo 'El fichero no existe o no es accesible';
        file_put_contents($log,' METHOD: DELETE FILE: '.$archivo." ERROR\n",FILE_APPEND);
        
    }





}


?>


