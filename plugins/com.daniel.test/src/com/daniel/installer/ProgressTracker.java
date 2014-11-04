package com.daniel.installer;


import javax.swing.Icon;
import javax.swing.SwingUtilities;
import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Extends the <tt>ServiceTracker</tt> to create a tracker for
 * <tt>SimpleShape</tt> services. The tracker is responsible for listener for
 * the arrival/departure of <tt>SimpleShape</tt> services and informing the
 * application about the availability of shapes. This tracker forces all
 * notifications to be processed on the Swing event thread to avoid
 * synchronization and redraw issues.
 **/
public class ProgressTracker extends ServiceTracker {
  // Flag indicating an added shape.
  private static final int ADDED = 1;
  // Flag indicating a modified shape.
  private static final int MODIFIED = 2;
  // Flag indicating a removed shape.
  private static final int REMOVED = 3;
  // The bundle context used for tracking.
  private BundleContext m_context;
  // The application object to notify.
  private InstallerFrame m_frame;

  /**
   * Constructs a tracker that uses the specified bundle context to track
   * services and notifies the specified application object about changes.
   * 
   * @param context The bundle context to be used by the tracker.
   * @param frame The application object to notify about service changes.
   **/
  public ProgressTracker(BundleContext context, InstallerFrame frame) {
    super(context, IProgress.class.getName(), null);
    m_context = context;
    m_frame = frame;
  }

  /**
   * Overrides the <tt>ServiceTracker</tt> functionality to inform the
   * application object about the added service.
   * 
   * @param ref The service reference of the added service.
   * @return The service object to be used by the tracker.
   **/
  public Object addingService(ServiceReference ref) {
	  IProgress progress = context.getService(ref);
    //SimpleShape shape = new DefaultShape(m_context, ref);
    processProgressOnEventThread(ADDED, ref, progress);
    return null;
  }

  /**
   * Overrides the <tt>ServiceTracker</tt> functionality to inform the
   * application object about the modified service.
   * 
   * @param ref The service reference of the modified service.
   * @param svc The service object of the modified service.
   **/
  public void modifiedService(ServiceReference ref, Object svc) {
    processProgressOnEventThread(MODIFIED, ref, (IProgress) svc);
  }

  /**
   * Overrides the <tt>ServiceTracker</tt> functionality to inform the
   * application object about the removed service.
   * 
   * @param ref The service reference of the removed service.
   * @param svc The service object of the removed service.
   **/
  public void removedService(ServiceReference ref, Object svc) {
    processProgressOnEventThread(REMOVED, ref, (IProgress) svc);
    //((DefaultShape) svc).dispose();
  }

  /**
   * Processes a received service notification from the <tt>ServiceTracker</tt>,
   * forcing the processing of the notification onto the Swing event thread if
   * it is not already on it.
   * 
   * @param action The type of action associated with the notification.
   * @param ref The service reference of the corresponding service.
   * @param shape The service object of the corresponding service.
   **/
  private void processProgressOnEventThread(int action, ServiceReference ref, IProgress progress) {
    if ((m_context.getBundle(0).getState() & (Bundle.STARTING | Bundle.ACTIVE)) == 0) {
      return;
    }

    try {
      if (SwingUtilities.isEventDispatchThread()) {
        processProgress(action, ref, progress);
      } else {
        SwingUtilities.invokeAndWait(new ProgressRunnable(action, ref, progress));
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Actually performs the processing of the service notification. Invokes the
   * appropriate callback method on the application object depending on the
   * action type of the notification.
   * 
   * @param action The type of action associated with the notification.
   * @param ref The service reference of the corresponding service.
   * @param shape The service object of the corresponding service.
   **/
  private void processProgress(int action, ServiceReference ref, IProgress progress) {
    //String name = (String) ref.getProperty(SimpleShape.NAME_PROPERTY);

    switch (action) {
      case MODIFIED:
        m_frame.removeProgress(progress);
        // Purposely let this fall through to the 'add' case to
        // reload the service.

      case ADDED:
        //Icon icon = (Icon) ref.getProperty(SimpleShape.ICON_PROPERTY);
        m_frame.addProgress(progress);
        if(progress != null)
        	progress.run();
        break;

      case REMOVED:
        m_frame.removeProgress(progress);
        break;
    }
  }

  /**
   * Simple class used to process service notification handling on the Swing
   * event thread.
   **/
  private class ProgressRunnable implements Runnable {
    private int m_action;
    private ServiceReference m_ref;
    private IProgress m_progress;

    /**
     * Constructs an object with the specified action, service reference, and
     * service object for processing on the Swing event thread.
     * 
     * @param action The type of action associated with the notification.
     * @param ref The service reference of the corresponding service.
     * @param shape The service object of the corresponding service.
     **/
    public ProgressRunnable(int action, ServiceReference ref, IProgress progress) {
      m_action = action;
      m_ref = ref;
      m_progress = progress;
    }

    /**
     * Calls the <tt>processShape()</tt> method.
     **/
    public void run() {
      processProgress(m_action, m_ref, m_progress);
    }
  }
}