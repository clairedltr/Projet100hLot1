function afficher_cacherabonnés(id)
{
    if(document.getElementById(id).style.visibility=="hidden")
    {
        document.getElementById(id).style.visibility="visible";
        document.getElementById('bouton_'+id).innerHTML='Cacher le contenu';
        var elmt = document.getElementById('div1');
        elmt.style.cssText = " position:relative;background-color:#845100;border-style:solid;border-top-left-radius:8px;border-top-right-radius:8px;border-bottom-left-radius:0px;border-bottom-right-radius:0px;border-bottom:none;margin:3%;margin-bottom:0%;padding:1%;width:91.5%;"
        document.getElementById('div2').style.cssText=" background-color:#845100;margin:3%; margin-top:0%; padding:1%; border-style:solid; border-color:black; border-top:none; border-bottom-left-radius:8px;border-bottom-right-radius:8px; width:91.5%"
 


    }
    else
    {
        document.getElementById(id).style.visibility="hidden";
        document.getElementById('bouton_'+id).innerHTML='Afficher le contenu';
        var elmt = document.getElementById('div1');
        elmt.style.cssText = "position:relative; background-color:#845100; border-style:solid; border-radius:8px; margin:3%; padding:1%;  width:91.5%; ";
        document.getElementById('div2').style.cssText="display:none;"
    }
    return true;
}