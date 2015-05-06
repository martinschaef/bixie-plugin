/**
 * 
 */
package bixie.plugin.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import bixie.plugin.util.UI;

/**
 * @author schaef
 *
 */
public class BixieSubtreeHandler extends AbstractBixieHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		UI.log("CLICK");
		getCompilationUnits();
		return null;
	}

	/**
	 * Returns the selected compilation units
	 * 
	 * @return Compilation unit
	 */
	protected Set<ICompilationUnit> getCompilationUnits() {
		Set<ICompilationUnit> result = new HashSet<ICompilationUnit>();
		// get workbench window
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (null == window)
			return null;

		// get selection
		ISelection selection = window.getSelectionService().getSelection();
		if (null == selection)
			return null;

		// return compilation unit
		TreeSelection treeSelection = (TreeSelection) selection;
		
		for (Object o : treeSelection.toList()) {
			if (o instanceof ICompilationUnit) {
				ICompilationUnit cu = (ICompilationUnit)o;
				result.add(cu);
				UI.log(cu.getPath().toOSString());
			} else if (o instanceof IJavaProject) {
				IJavaProject  jp = (IJavaProject)o;
				//TODO
			} else if (o instanceof IPackageFragment) {
				IPackageFragment pf = (IPackageFragment)o;
				//TODO
			} else if (o instanceof IPackageFragmentRoot) {
				IPackageFragmentRoot pfr = (IPackageFragmentRoot)o;
				//TODO
			} else {
				UI.log(o.getClass().toString());	
			}			
		}
		return result;
	}
	
	
}
