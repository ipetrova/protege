package org.protege.editor.core.ui.workspace;

import org.java.plugin.registry.Extension;
import org.protege.editor.core.plugin.AbstractApplicationPluginLoader;
import org.protege.editor.core.plugin.PluginExtensionMatcher;
import org.protege.editor.core.plugin.PluginParameterExtensionMatcher;
import org.protege.editor.core.plugin.PluginProperties;
/*
 * Copyright (C) 2007, University of Manchester
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Mar 18, 2006<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 * <p/>
 * A utility class that loads <code>WorkspaceTabPlugin</code>s.  The
 * tab plugins are filtered so that <code>WorkspaceTabPlugin</code>s
 * that apply to a specific workspace are loaded.
 */
public class WorkspaceTabPluginLoader extends AbstractApplicationPluginLoader<WorkspaceTabPlugin> {

    private TabbedWorkspace workspace;


    /**
     * Creates a <code>WorkspaceTabPluginLoader</code> that will
     * load <code>WorkspaceTabPlugin</code>s that are applicable
     * to the specified <code>Workspace</code>.
     */
    public WorkspaceTabPluginLoader(TabbedWorkspace workspace) {
        super(WorkspaceTabPluginJPFImpl.ID);
        this.workspace = workspace;
    }


    /**
     * This method needs to be overriden to provide a
     * <code>PluginExtensionMatcher</code>, which is used to filter
     * the plugin extensions to a desired subset.
     */
    protected PluginExtensionMatcher getExtensionMatcher() {
        PluginParameterExtensionMatcher matcher = new PluginParameterExtensionMatcher();
        matcher.put(PluginProperties.EDITOR_KIT_PARAM_NAME, workspace.getEditorKit().getId());
        return matcher;
    }


    /**
     * This method needs to be overriden to create an instance
     * of the desired plugin, based on the plugin <code>Extension</code>
     * @param extension The <code>Extension</code> that describes the
     *                  Java Plugin Framework extension.
     * @return A plugin object (typically some sort of wrapper around
     *         the extension)
     */
    protected WorkspaceTabPlugin createInstance(Extension extension) {
        return new WorkspaceTabPluginJPFImpl(workspace, extension);
    }
}
