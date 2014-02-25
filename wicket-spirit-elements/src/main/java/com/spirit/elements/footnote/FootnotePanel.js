/**
 * Created by PHT on 25/02/14.
 */
(function reorderFootnote(scopeAreaComponentId) {
    var $scopeArea = scopeAreaComponentId ? $('#' + scopeAreaComponentId) : $(window);
    var $footnotesInScopeArea = $scopeArea.find('.footnoteWSE');

    var uniqueTopLeftFootnotesInScopeArea = {};
    $footnotesInScopeArea.each(function() {
        var footnoteIndex = $(this).text();
        var footnoteOffset = $(this).offset();

        var topLeftFootnoteForIndex = uniqueTopLeftFootnotesInScopeArea[footnoteIndex];
        if (typeof topLeftFootnoteForIndex === 'undefined') {
            uniqueTopLeftFootnotesInScopeArea[footnoteIndex] = footnoteOffset;
        } else if (footnoteOffset.top < topLeftFootnoteForIndex.top) {
            uniqueTopLeftFootnotesInScopeArea[footnoteIndex] = footnoteOffset;
        } else if (footnoteOffset.top === topLeftFootnoteForIndex.top && footnoteOffset.left < topLeftFootnoteForIndex.left) {
            uniqueTopLeftFootnotesInScopeArea[footnoteIndex] = footnoteOffset;
        }
    });

    for (member in uniqueTopLeftFootnotesInScopeArea) {
        if (uniqueTopLeftFootnotesInScopeArea.hasOwnProperty(member)) {
            console.log(member + ": " + uniqueTopLeftFootnotesInScopeArea[member]);
        }
    }
} (null));