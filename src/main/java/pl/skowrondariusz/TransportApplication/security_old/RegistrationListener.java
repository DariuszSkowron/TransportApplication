package pl.skowrondariusz.TransportApplication.security_old;

//import pl.skowrondariusz.TransportApplication.model.User;
//import pl.skowrondariusz.TransportApplication.service.IUserService;


//@Component
//public class RegistrationListener implements
//        ApplicationListener<OnRegistrationCompleteEvent> {
//
//    @Autowired
//    private IUserService service;
//
//    @Autowired
//    private MessageSource messages;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
//        this.confirmRegistration(event);
//    }
//
//    private void confirmRegistration(OnRegistrationCompleteEvent event) {
//        User user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        service.createVerificationToken(user, token);
//
//        String recipientAddress = user.getEmail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl
//                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
//
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
//        mailSender.send(email);
//    }
//}