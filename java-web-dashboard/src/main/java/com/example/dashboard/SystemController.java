package com.example.dashboard;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    
    @GetMapping("/")
    public String dashboard(Model model) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        
        CentralProcessor cpu = hal.getProcessor();
        GlobalMemory memory = hal.getMemory();
        
        model.addAttribute("hostname", os.getNetworkParams().getHostName());
        model.addAttribute("cpuLoad", cpu.getSystemLoadAverage(1)[0]);
        model.addAttribute("memoryUsed", 
            (memory.getTotal() - memory.getAvailable()) * 100 / memory.getTotal());
        model.addAttribute("os", os);
        
        return "dashboard";
    }
}
