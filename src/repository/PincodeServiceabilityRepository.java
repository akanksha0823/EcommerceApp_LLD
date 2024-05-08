package repository;

import model.PaymentMode;
import model.PinCodeServiceability;

import java.util.HashMap;

public class PincodeServiceabilityRepository {
    HashMap<String, HashMap<String, PaymentMode>> pinCodes=new HashMap<>();

    public boolean createPinCodeServiceability(String sourcePinCode, PinCodeServiceability pinCodeServiceability){
        if(pinCodes.get(sourcePinCode)==null){
            HashMap<String, PaymentMode> destinationCode = new HashMap<>();
            pinCodes.put(sourcePinCode,destinationCode);
        }
        pinCodes.get(sourcePinCode).put(pinCodeServiceability.getDestinationPinCode(), pinCodeServiceability.getPaymentMode());

        return true;
    }

    public HashMap<String, PaymentMode> getAllDestinationPincodes(String sourcePinCode){
        return pinCodes.get(sourcePinCode);
    }
}
