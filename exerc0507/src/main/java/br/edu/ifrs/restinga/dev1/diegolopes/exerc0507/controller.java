package br.edu.ifrs.restinga.dev1.diegolopes.exerc0507;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



@RestController
public class controller {
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("teste");
    }

    @RequestMapping(value = "/imc/{peso}/{altura:.+}", produces = "application/json")
    @ResponseBody
    public String getImc(@PathVariable("peso") int peso, @PathVariable("altura") double altura) {
        JSONObject retorno = new JSONObject();
        double massa = 0.0;
        String classificacao = "";
        NumberFormat nf =  new DecimalFormat("#.##");

        massa = peso / (altura * altura);
        if (massa < 18.5) {
            classificacao = "Magreza";
        } else if (massa >= 18.5 && massa <= 24.9) {
            classificacao = "Saudável";
        }
        else if (massa >= 25 && massa <= 29.9) {
            classificacao = "Sobrepeso";
        }
        else if (massa >= 30 && massa <= 34.9) {
            classificacao = "Obesidade Grau I";
        } else if (massa >= 35 && massa <= 39.9) {
            classificacao = "Obesidade Grau II (Severa)";
        } else {
            classificacao = "Obesidade Grau III (Mórbida)";
        }

        retorno.put("massa", nf.format(massa));
        retorno.put("classificacao", classificacao);

        return retorno.toString();
    }

    @RequestMapping(value = "/diaSemana/{ano}/{mes}/{dia}", produces = "application/json")
    @ResponseBody
    public String getWeekDay(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes, @PathVariable("dia") Integer dia) {
        JSONObject retorno = new JSONObject();
        LocalDate dt = LocalDate.of(ano, mes, dia);
        DayOfWeek weekDay;

        weekDay = dt.getDayOfWeek();
        retorno.put("data", dia.toString() + "/" + mes.toString() + "/" + ano.toString());
        retorno.put("diaSemana", weekDay.getDisplayName(TextStyle.FULL_STANDALONE,new Locale("pt", "br")).toUpperCase());

        return retorno.toString();
    }

    @RequestMapping(value = "/calculadora/adicao", produces = "application/json")
    @ResponseBody
    public String getSum(@RequestParam("n1") Integer num1, @RequestParam("n2") Integer num2) {
        JSONObject retorno = new JSONObject();
        Integer result = num1 + num2;
        retorno.put("resultado", result);
        return retorno.toString();
    }

    @RequestMapping(value = "/calculadora/subtracao", produces = "application/json")
    @ResponseBody
    public String getSubtraction(@RequestParam("n1") Integer num1, @RequestParam("n2") Integer num2) {
        JSONObject retorno = new JSONObject();
        Integer result = num1 - num2;
        retorno.put("resultado", result);
        return retorno.toString();
    }

    @RequestMapping(value = "/calculadora/multiplicacao", produces = "application/json")
    @ResponseBody
    public String getMultiplication(@RequestParam("n1") Integer num1, @RequestParam("n2") Integer num2) {
        JSONObject retorno = new JSONObject();
        Integer result = num1 * num2;
        retorno.put("resultado", result);
        return retorno.toString();
    }

    @RequestMapping(value = "/calculadora/divisao", produces = "application/json")
    @ResponseBody
    public String getDivision(@RequestParam("n1") Integer num1, @RequestParam("n2") Integer num2) {
        JSONObject retorno = new JSONObject();
        NumberFormat nf =  new DecimalFormat("#.##");
        Double result = num1.doubleValue() / num2.doubleValue();
        retorno.put("resultado", nf.format(result));
        return retorno.toString();
    }


}
