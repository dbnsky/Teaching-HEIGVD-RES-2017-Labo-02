package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.InfoCommandResponse;
import ch.heigvd.res.labs.roulette.net.protocol.RandomCommandResponse;
import ch.heigvd.res.labs.roulette.net.server.ClientWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the client side of the protocol specification (version 1).
 * 
 * @author Olivier Liechti
 */
public class RouletteV1ClientImpl implements IRouletteV1Client
{

  private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());

  private Socket socket;

  private InfoCommandResponse icr;

  private String serverRead() throws IOException
  {
    return (new BufferedReader(new InputStreamReader(socket.getInputStream()))).readLine();
  }

  private void serverWrite(String toWrite) throws IOException
  {
    PrintWriter out =
            new PrintWriter(socket.getOutputStream(), true);
    out.println(toWrite);
  }

  @Override
  public void connect(String server, int port) throws IOException
  {
    socket = new Socket("localhost", 1313);
    LOG.info(socket.toString());
    LOG.info(serverRead());
  }

  @Override
  public void disconnect() throws IOException
  {
    serverWrite(RouletteV1Protocol.CMD_BYE);
    socket.close();
  }

  @Override
  public boolean isConnected()
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void loadStudent(String fullname) throws IOException
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void loadStudents(List<Student> students) throws IOException
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException
  {
      serverWrite(RouletteV1Protocol.CMD_RANDOM);
      Student gerard = new Student();
      RandomCommandResponse rcr = JsonObjectMapper.parseJson(serverRead(),
              RandomCommandResponse.class);

      if(rcr.getError() != null) throw new EmptyStoreException();

      gerard.setFullname(rcr.getFullname());
      return gerard;
  }

  @Override
  public int getNumberOfStudents() throws IOException
  {
    serverWrite(RouletteV1Protocol.CMD_INFO);
    return JsonObjectMapper.parseJson(serverRead(),InfoCommandResponse.class).getNumberOfStudents();
  }

  @Override
  public String getProtocolVersion() throws IOException
  {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
