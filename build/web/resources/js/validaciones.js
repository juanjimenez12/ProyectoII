    $(function(){
     //agregar expresion 
    $.validator.addMethod('valCodigo',function(value,element )
    {
        return this.optional(element) || /^[a-zA-Z0-9_]/ .test(value);
    });
    $.validator.addMethod('valNombre',function(value,element)
    {
       
        return this.optional(element) || /^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$/.test(value);
    });
    $.validator.addMethod('valApellido',function(value,element)
    {
        return this.optional(element) || /^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$/.test(value);
    });
    $.validator.addMethod('valCorreo',function(value,element)
    {
        return this.optional(element) || /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(value);
    });
    $.validator.addMethod('valCohorte',function(value,element)
    {
        var current_year = new Date().getFullYear().toString().split("");
        var reg=new RegExp("^(200[0-9]|[0-"+current_year[0]+"][0-"+current_year[1]+"][0-"+current_year[2]+"][0-"+current_year[3]+"])$");
        return this.optional(element) || reg.test(value);
    });
    $.validator.addMethod('valNombreTutor',function(value,element)
    {
        return this.optional(element) || /^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$/.test(value);
    });

    $.validator.addMethod('valSemestre', function(value,element)
    {
        alert('s');
        alert($(element).value);
        /*var seleccion = document.getElementById("valSemestre");
        return (seleccion.value === "Seleccionar");*/
    });
    //hasta aqui ...................
    $.validator.setDefaults( {
            submitHandler: function () {
                    alert( "submitted!" );
            }
    } );

    $( "#FormRegistrar" ).validate( {
            rules: {
                    //se agrega la funcion nueva 
                    valCodigo: {
                            required:true,
                            minlength: 7,
                            maxlength: 20,
                            valCodigo:true
                            },
                    valNombre: {
                            required:true,
                            minlength: 3,
                            maxlength: 20,
                            valNombre:true
                            },
                    valApellido: {
                            required:true,
                            minlength: 6,
                             maxlength: 20,
                            valApellido:true
                    },
                    valCorreo: {
                            required:true,	
                            minlength: 10,
                            maxlength: 30,					
                            valCorreo:true
                    },
                    valCohorte: {
                            required:true,
                            valCohorte:true
                    },
                    valNombreTutor: {
                            required:true,
                            minlength: 3,
                            maxlength: 45,
                            valNombreTutor:true
                    },

                    valSemestre: {
                        valSemestre: false
                    }

            },
            messages: {
                    valCodigo: {
                            valCodigo :"El código puede contener letras, números, guión bajo y su longitud debe ser de 7 a 20 caracteres",
                            minlength: "El código puede contener letras, números, guión bajo y su longitud debe ser de 7 a 20 caracteres",
                            maxlength: "El código puede contener letras, números, guión bajo y su longitud debe ser de 7 a 20 caracteres",
                            required: "El código es obligatorio"
                    },
                    valNombre: {
                            valNombre :"El nombre solo puede contener letras y su longitud debe ser de 3 a 20 caracteres",
                            minlength: "El nombre solo puede contener letras y su longitud debe ser de 3 a 20 caracteres",
                            maxlength: "El nombre solo puede contener letras y su longitud debe ser de 3 a 20 caracteres",
                            required: "El nombre del estudiante es obligatorio"

                    },
                    valApellido: {
                            valApellido :"El apellido solo puede contener letras y su longitud debe ser de 6 a 20 caracteres",
                            minlength: "El apellido solo puede contener letras y su longitud debe ser de 6 a 20 caracteres",
                            maxlength: "El apellido solo puede contener letras y su longitud debe ser de 6 a 20 caracteres",
                            required: "El apellido del estudiante es obligatorio"
                    },
                    valCorreo: {
                            valCorreo :"El formato de este correo es incorrecto y su longitud debe ser de 10 a 30 caracteres",
                            minlength: "El formato de este correo es incorrecto y su longitud debe ser de 10 a 30 caracteres",
                            maxlength: "El formato de este correo es incorrecto y su longitud debe ser de 10 a 30 caracteres",
                            required: "El correo del estudiante es obligatorio"
                    },
                    valCohorte: {
                            valCohorte :"El cohorte debe ser un año válido, cuyo valor sea mayor o igual a 2000 y menor o igual al año actual",
                            minlength: "El cohorte debe ser un año válido, cuyo valor sea mayor o igual a 2000 y menor o igual al año actual",
                            maxlength: "El cohorte debe ser un año válido, cuyo valor sea mayor o igual a 2000 y menor o igual al año actual",
                            required: "El valCohorte es obligatorio"
                    },
                    valNombreTutor: {
                            valNombreTutor :"El nombre del tutor solo puede contener letras y su longitud debe ser de 3 a 45 caracteres",
                            minlength: "El nombre del tutor solo puede contener letras y su longitud debe ser de 3 a 45 caracteres",
                            maxlength: "El nombre del tutor solo puede contener letras y su longitud debe ser de 3 a 45 caracteres",
                            required: "El nombre del tutor es obligatorio"
                    },
                    valSemestre: {
                            valSemestre :"Seleccione un semestre"
                    },
                    agree1: "Please accept our policy"
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                
              
                    // Add the `help-block` class to the error element
                    error.addClass( "help-block" );

                    // Add `has-feedback` class to the parent div.form-group
                    // in order to add icons to inputs
                    element.parents( ".col-sm-5" ).addClass( "has-feedback" );

                    if ( element.prop( "type" ) === "checkbox" ) {
                            error.insertAfter( element.parent( "label" ) );
                    } else {
                        
                            error.insertAfter( element );
                    }

                    // Add the span element, if doesn't exists, and apply the icon classes to it.
                    if ( !element.next( "span" )[ 0 ] ) {
                            
                           $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
                            
                    }
            },
            success: function ( label, element ) {
                    // Add the span element, if doesn't exists, and apply the icon classes to it.
                    if ( !$( element ).next( "span" )[ 0 ] ) {
                            $( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element) );
                    }
            },
            highlight: function ( element, errorClass, validClass ) {
                
                    $( element).parents( ".col-md-6" ).addClass( "has-error" ).removeClass( "has-success" );
                    $( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
                    
            },
            unhighlight: function ( element, errorClass, validClass ) { 
                     //alert(element.name);
                    $( element ).parents( ".col-md-6" ).addClass( "has-success" ).removeClass( "has-error" );
                    $( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
                    //$(element).attr('name',obtenername);
                    var identificador = $(element).attr('onfocus');
                    var name = $(element).attr('name');
                    
                    $(element).attr('name', (identificador + "" + name));
            }
    } );

    });
