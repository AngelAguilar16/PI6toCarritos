<?php

class messages{
    public function showMessage($sessionName, $type){
        if($type === "success"){
            if(isset($_SESSION[$sessionName])): 
                echo '
                <div class="alert alert-success">
                    <div class="alert-heading">
                        <span class="alert-icon">&check;</span>
                        <span class="alert-heading-text"><strong>Success</strong></span>
                    </div>
                    <div class="alert-body">
                        '. $_SESSION[$sessionName] .'
                    </div>
                </div>';
            endif;
            unset($_SESSION[$sessionName]);
        }
        else if($type === "error"){
            if(isset($_SESSION[$sessionName])): 
                echo '
                <div class="alert alert-danger">
                    <div class="alert-heading">
                        <span class="alert-icon">&#10005;</span>
                        <span class="alert-heading-text"><strong>Error</strong></span>
                    </div>
                    <div class="alert-body">
                        '.$_SESSION[$sessionName].'
                    </div>
                </div>';
            endif;
            unset($_SESSION[$sessionName]);
        }
        else if($type === "verify"){
            if(isset($_SESSION[$sessionName])):
                echo '
                <div class="verify-box">
                    <div class="verify-icon">
                        <span class="icon">&#10004;</span>
                    </div>
                    <div class="verify-heading">
                        <h1>Exito</h1>
                    </div>
                    <div class="verify-p">
                        '. $_SESSION[$sessionName].'
                    </div>
                    <div class="verify-btn">
                        <a href="dashboard.php" class="btn btn-sweet">Continue &rarr;</a>
                    </div>
                </div>';
            endif;
            unset($_SESSION[$sessionName]);
        }
    }
}

?>