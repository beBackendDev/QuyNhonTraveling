'use strict';

/**
 * navbar toggle in --Mobile
 *  
 */

const /**{NodeElement} */ $navbar = document.querySelector("[data-navbar]");
const /**{NodeElement} */ $navToggler = document.querySelector("[data-nav-toggler]");

$navToggler.addEventListener("click", () => $navbar.classList.toggle("active"));

/**
 * header scroll state
 *  
 */
const /**{NodeElement} */ $header = document.querySelector("[data-header]");
window.addEventListener("scroll", e => {
    $header.classList[window.scrollY > 50 ? "add" : "remove"]("active");

});

/**
 *      --add favorite
 */
const /**{NodeElement} */ $toggleBtns = document.querySelectorAll("[data-toggle-btn]");

$toggleBtns.forEach($toggleBtn => {
    $toggleBtn.addEventListener("click", () => {
        $toggleBtn.classList.toggle("active");
    });
});

/**
 *      --open close navbar
 */
const /**{NodeElement} */ $toggleNavs = document.querySelectorAll("[data-nav-toggler]");

$toggleNavs.forEach($toggleNav => {
    $toggleNav.addEventListener("click", () => {
        $toggleNav.classList.toggle("active");
    });
});

