<?PHP

$file_handle = fopen("widgets.csv", "r");

while (!feof($file_handle) ) {

$line_of_text = fgetcsv($file_handle, 1024);

print $line_of_text[0]."\n" ;
print $line_of_text[1]."\n" ;
print $line_of_text[2]."\n" ;

//. $line_of_text[1]. $line_of_text[2] . "<BR>";

}

fclose($file_handle);

?>
