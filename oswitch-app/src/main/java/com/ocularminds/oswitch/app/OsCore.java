package com.ocularminds.oswitch.app;

import com.ocularminds.oswitch.core.CoreBankingProvider;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.PostChannel;
import org.jpos.iso.packager.PostPackager;
import org.jpos.util.LogSource;
import org.jpos.util.SimpleLogListener;
import org.jpos.util.ThreadPool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import java.io.File;
import java.io.IOException;
import java.security.CodeSource;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OsCore implements ISORequestListener {

    final Logger logger = Logger.getLogger(OsCore.class.getName());
    private CoreBankingProvider provider;
    private Map<String, String> processors;

    public OsCore(final CoreBankingProvider prov, final Map<String, String> codes) {
        this.provider = prov;
        this.processors = codes;
    }

    public boolean process(ISOSource source, ISOMsg request) {

        logger.log(Level.INFO, "> OsCore.process");
        ISOMsg response = (ISOMsg) request.clone();
        try {
            String processCode = response.getString(3);
            ProcessorType pt = ProcessorType.valueOf(this.processors.get(processCode));
            response = pt.process(provider, response);
            response.setResponseMTI();
            response.set(39, "00");
            source.send(response);
        } catch (ISOException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new FileSystemXmlApplicationContext(String.format("%s%s%s",
                        new File("").getAbsolutePath(), File.separator, "os-context.xml"));
        CoreBankingProvider provider = (CoreBankingProvider) ctx.getBean("coreBankingProvider");
        CoreProcessor cp = (CoreProcessor) ctx.getBean("coreProcessor");
        OsService service = (OsService) ctx.getBean("service");
        Map<String, String> processors = cp.getProcessors();
        org.jpos.util.Logger loga = new org.jpos.util.Logger();
        loga.addListener(new SimpleLogListener(System.out));
        ServerChannel channel = new PostChannel(new PostPackager());
        ((LogSource) channel).setLogger(loga, "channel");
        ISOServer server = new ISOServer(8000, channel, new ThreadPool(1, 100, "oscore-pool"));
        server.setLogger(loga, "server");
        server.addISORequestListener(new OsCore(provider, processors));
        new Thread(server).start();
    }
}
