# Reviews
## Reviewer 1
1. VMDV is a promising tool. Visualizing proof trees and Kripke structures is a natural idea, and most existing visualization tools do not take advantage of 3D graphics software/hardware. The paper makes the plausible claim that adding another spatial dimension to visualizations will make proof visualization more useful to engineers and students.
2. The tool tracks the relationship between proof trees and Kripke structures (Fig. 10,11). This contribution seems like a promising approach toward trying to understand the composition of a large, automatically generated proofs -- especially for modal and dynamic logics.
3. The primary weakness of this paper is in its evaluation of VMDV. The paper does not contain a thorough comparison of VMDV with existing (2D) proof visualization systems. The paper's premise is that existing graph-based visualization systems have not sufficiently decreased the barriers for formal methods. The paper identifies proof size is one barrier. However, the paper does not validate the hypothesis that VMDV improves comprehensibility of large proofs. The Coq example is small, and the river crossing puzzle is small relative to modern proof developments (and even relative to some problems that have been used in pedagogical settings). Evaluating VMDV on existing large verification efforts would provide more convincing evidence of the paper's claims.
4. This paper's contributions in tooling, and the idea of visualizing a proof structure along side a semantic structure, are interesting contributions. Improved evaluation -- organized around more concrete testable hypotheses about the precise differences in usability/utility between 2D and 3D proof visualizations and focusing on larger proofs -- would significantly improve the paper.
5. There are several typos and grammar errors in the paper.

## Reviewer 2
1. My main problem with this paper is that I do not understand the specificity of the proposed tool compared to existing, generic, 3D graph visualization tool. A quick search on 3D graph visualization provide mature results (sometimes old) where graphs can be modified and analyzed by external plugins (see http://wilma.sourceforge.net/ for a simple example). It is important to understand the specificity of the proposed approach compared to generic graph visualization in order to be in the scope of the TASE conference.
2. Additionally, after downloading and trying the tool, I was unable to reproduce some features amongst which the extraction of sub graph (like Figures 5 or 11) or the interactive highlighting of the State and proof tree graphs like in Figures 8 and 9.

## Reviewer 3
1. While an example of its use with a SCTL prover is given, this lacks a clear idea of how the visualisation tool is told about the formulas of SCTL. What is the format of the messages being exchanged. How would this change if SCTL was replaed vy CoQ, as suggested int he paper. Or Isabelle/HOL? HOL4? PVS?
2. Also, many of the images shown are either too simple or too detailed to get a good impression of what the tool does.

## Reviewer 4
1.  It
seems to the reviewer that the proposed technique is more or less
standard from the information visualization technology point of view,
and the key point from the TASE point of view lies in findings
obtained from use cases and some convincing examples.  Unfortunately,
only a single use case is given (the second "small example of Coq" is
"still under development"), and the proposed technique is not
sufficiently discussed, evaluated, and justified (the authors could
have spent 1.5 more pages to do so).
2. The author also discusses enhancing Coq with VMDV.  However, proofs
handled by proof assistants such as Coq and those obtained from
automated theorem provers or model checkers are different in many
respects in practice, which the authors may not be fully aware (e.g.,
the Abstract mentions automated theorem provers only).  Accordingly,
design principles of visualization tools for these two can be quite
different as well.
3. In summary, the paper could possibly be accepted tool demonstration at
this stage, but is not yet strong enough for a full technical paper.
4. Suggestion: use more space on running examples and use cases, and less
space on SCTL itself and visualization techniques (except for novel
ideas in them).