


 $(document).ready(function(){



              $("#alert-basic").click(function(){
                  swal("Hello world!");
              });

              $("#alert-title").click(function(){
                  swal("Here's the title!", "...and here's the text!");
              });

              $("#alert-success").click(function(){
                  swal("Good job!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,", "success");
              });

              $("#alert-error").click(function(){
                  swal("Somthing Wrong!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,", "error");
              });

              $("#alert-info").click(function(){
                  swal("Information!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,", "info");
              });

              $("#alert-warning").click(function(){
                  swal("Warning!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,", "warning");
              });


              $("#confirm-btn-alert").click(function(){

                  swal({
                    title: "Are you sure?",
                    text: "Once deleted, you will not be able to recover this imaginary file!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                  })
                  .then((willDelete) => {
                    if (willDelete) {
                      swal("Poof! Your imaginary file has been deleted!", {
                        icon: "success",
                      });
                    } else {
                      swal("Your imaginary file is safe!");
                    }
                  });

              });



          });