<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <style>
        /* Custom CSS for background */
        .custom-bg {
            background-color: gray; /* Change this to your desired background color */
        }

        /* Animation for the form */
        @keyframes moveForm {
            0% {
                transform: translateY(-50px); /* Move the form up by 50px */
                opacity: 0;
            }
            100% {
                transform: translateY(0); /* Move the form back to its original position */
                opacity: 1;
            }
        }

        /* Apply animation to the form */
        .animate-form {
            animation: moveForm 0.9s ease-in-out;
        }
    </style>
</head>
<body>
<section class="vh-100 custom-bg">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center animate-form"> <!-- Add animate-form class here -->

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 style="text-transform: capitalize" class="mb-2">Login</h2>
                            <p class="text-white-50 mb-5">Please enter your login and password!</p>

                            <form action="NewServlet">
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="uname" style="margin-right: 350px">Username</label>
                                    <input type="text" id="uname" name="uname" class="form-control form-control-lg" />
                                </div>
                                <label class="form-label" for="pass" style="margin-right: 350px">Password</label>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="pass" name="pass" class="form-control form-control-lg" />
                                </div>
                                <h5 style="color: red">${error}</h5>
                                <input class="btn btn-outline-light btn-lg px-5" type="submit" name="log" value="Login" />
                            </form>

                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
                            </div>

                        </div>

                        <div>
                            <p class="mb-0">Don't have an account? <a href="Register.jsp" class="text-white-50 fw-bold">Sign Up</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>
