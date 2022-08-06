<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<html>
<head>
      <title>Menu Thinspo</title>
       <sj:head jqueryui="true" jquerytheme="redmond" />
       	<link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
       <script type="text/javascript">
          $.subscribe('seleccionMenu', function(event,data) {
    		  var item = event.originalEvent.data.rslt.obj;
    		  if (item.attr("id").indexOf('.action')!=-1)//Es un nodo con action de Struts
    		        parent.recargaContenido(item.attr("id"));
       });
       </script>
      
</head>
<body class="thinspo">
 
<h3>Menu Thinspo</h3>
        <s:url id="urlCargarMenu" action="cargarMenu.action"/>
        <sjt:tree 
                jstreetheme="default"
                id="menu" 
                href="%{urlCargarMenu}"
                animation="1" 
                onClickTopics="seleccionMenu"
        />
</body>
</html>
