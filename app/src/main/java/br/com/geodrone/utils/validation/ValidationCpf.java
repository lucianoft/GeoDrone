package br.com.geodrone.utils.validation;

public class ValidationCpf {

    public boolean validate(Long cpf) {
        try {
            if (cpf == null) {
                return true;
            }

            String str = cpf.toString();
            if (str.length() < 11) {
                for (int i = str.length(); i < 11; i++) {
                    str = "0" + str;
                }
            }

            if (str.length() > 11) {
                return false;
            }
            if ("00000000000".equals(str)) {
                return false;
            }

            String numDig = str.substring(0, 9);
            return calculateDigit(numDig).equals(str.substring(9, 11));
        } catch (Exception ex) {
            return false;
        }
    }

    private String calculateDigit(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }

}
